from kafka import KafkaProducer
import json
import time
import fakeDataGenerator
import threading

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

sports_messages = fakeDataGenerator.generate_sports_messages(10)
politics_messages = fakeDataGenerator.generate_politics_messages(10)

# Send both sports and politics messages concurrently
thread_sports = threading.Thread(target=send_messages, args=("data", sports_messages))
thread_politics = threading.Thread(target=send_messages, args=("data", politics_messages))

thread_sports.start()
thread_politics.start()

thread_sports.join()
thread_politics.join()

# Flush and close the producer
producer.flush()
producer.close()


