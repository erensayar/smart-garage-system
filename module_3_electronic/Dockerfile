FROM python:3.8.0-alpine

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

RUN apk update \
    && apk add gcc python3-dev musl-dev

RUN mkdir -p /home/app
RUN addgroup -S app && adduser -S app -G app

ENV HOME=/home/app
ENV APP_HOME=/home/app/web
WORKDIR $APP_HOME

RUN pip install --upgrade pip
COPY ./requirements.txt $APP_HOME/requirements.txt
RUN pip install -r requirements.txt

COPY . $APP_HOME

USER root

ENTRYPOINT ["python", "run.py"]
