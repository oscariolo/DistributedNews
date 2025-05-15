from kafka import KafkaProducer
import json
import time

# Initialize KafkaProducer with JSON serializer
producer = KafkaProducer(
	bootstrap_servers='localhost:9092',
	value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

def send_messages(topic_name, messages):
	for msg in messages:
		# Ensure every message includes "topic": "atopic"
		msg['topic'] = 'atopic'
		producer.send(topic_name, msg)
		print(f"Sent to {topic_name}: {msg}")
		time.sleep(0.5)  # Simulate delay

# Prepare sample data for sports and politics topics
sports_messages = [
	{"topicName":"sports","headline": "Team wins championship", "content": "An amazing game."},
	{"topicName":"sports","headline": "Star player injured", "content": "Game postponed."},
	# ...existing data...
]

politics_messages = [
	{"topicName":"politics","headline": "Election results announced", "content": "Major victory."},
	{"topicName":"politics","headline": "New policy introduced", "content": "Economic reforms."},
	# ...existing data...
]

# Send messages to respective topics
send_messages("data", sports_messages)
send_messages("data", politics_messages)

# Flush and close the producer
producer.flush()
producer.close()


