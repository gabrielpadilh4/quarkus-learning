podman build -t wisdom-db .
podman run -d --rm -p 5430:5432 --name wisdom-pg --env POSTGRES_DB=wisdom_pet --env POSTGRES_PASSWORD=postgres localhost/wisdom-db