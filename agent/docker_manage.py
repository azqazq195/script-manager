import docker

client = docker.from_env()

def container_list():
  return client.containers.list(all=True)

def image_list():
  return client.images.list(all=True)