from fastapi import FastAPI
from docker_manage import *

app = FastAPI()


@app.get("/")
async def root():
  return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
  for image in image_list():
    print(image)

  for container in container_list():
    print(container)

  return {"message": f"Hello {name}"}
