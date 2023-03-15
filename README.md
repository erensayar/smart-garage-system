# Requirements
- JDK 8+ [here](https://openjdk.org/install/)
- Maven [here](https://maven.apache.org/download.cgi)
- Python3 [here](https://www.python.org/downloads/)
- Pip [here](https://pip.pypa.io/en/stable/cli/pip_install/)
- Docker Engine [here](https://docs.docker.com/desktop/)
- Docker Compose [here](https://docs.docker.com/compose/install/)


# Run

- Download tesseract-ocr binaries. Visit [here](https://tesseract-ocr.github.io/tessdoc/4.0-with-LSTM.html#400-alpha-for-windows) and follow instruction for your system.

- **Create Python Environment**
    ```
    pip install virtualenv

    cd license_plate_recognition/module_1_vision

    virtualenv venv
    ```
    - **for Gnu/Linux Systems**
        ```
        source venv/bin/activate
        ```
    
    - **for Windows systems**
        ```
        \venv\Scripts\activate.bat
        ```

- **Start** 

    ```
    pip install -r requirements.txt

    python run.py
    ```

## If You Want Run In The Container 

1. There are some dependecies for raspberry pi. If you want to run this project in pi, you should run this line

    ```
    docker-compose -f docker-compose-pi.yml up --build
    ```

* Otherwise you should run this line
    
    ```
    docker-compose up --build
    ```