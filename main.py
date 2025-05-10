import requests
import random
from faker import Faker

fake = Faker()

URL = "http://localhost:8080/api/books"

for _ in range(10000):  # Change to 100000 if needed
    data = {
        "title": fake.sentence(nb_words=4),
        "author": fake.name(),
        "isbn": random.randint(1000000000000, 9999999999999),  # 13-digit ISBN
        "publicationYear": str(random.randint(1950, 2024))
    }

    response = requests.post(URL, json=data)

    if response.status_code != 201:
        print(f"Failed to insert: {response.status_code} - {response.text}")
