# Installation

- Be sure you have an Python3 and pip, installed in your system.

    - further information about [Python](https://www.python.org/downloads/)

- if you dont have Docker installed system please follow [this](https://docs.docker.com/desktop/) instruction for your system

- also you must install docker-compose extension. you can follow [this](https://docs.docker.com/compose/install/) page for installing docker-compose

# Usage

### Running Docker containers
- Clone or download the repository
    - If you have git software in your system you can use this command.
    ```sh
    git clone https://github.com/mrtmrcbr/license_plate_recognition.git
    ```
    - Otherwise visit [here](https://github.com/mrtmrcbr/license_plate_recognition) and press clone button and download as zip and extract it.


```sh
cd license_plate_recognition
```

There are some dependecies for raspberry pi. if you want to run this project in pi, you should run this line
- **Build and run into Raspberry pi**
```sh
docker-compose -f docker-compose-pi.yml up --build
```

- **Otherwise you should run**
```sh
docker-compose up --build
```

### Running vision module-1

- Download tesseract-ocr binaries visit [here](https://tesseract-ocr.github.io/tessdoc/4.0-with-LSTM.html#400-alpha-for-windows) and follow instruction for your system

- Create Python environment
```sh
pip install virtualenv

cd license_plate_recognition/module_1_vision

virtualenv venv
```
- **for Gnu/linux systems**
```sh
source venv/bin/activate
```

- **for Windows systems**
```sh
\venv\Scripts\activate.bat
```

- After activating environment install Python dependecies and run web server
```sh
pip install -r requirements.txt

python run.py
```