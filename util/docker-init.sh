#!/usr/bin/env bash

set -Ceuo pipefail

cd "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

cd ../docker
docker-compose stop && \
docker-compose rm -f && \
docker-compose build && \
docker-compose up