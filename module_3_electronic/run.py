from flask import Flask,jsonify,abort,make_response
from gpiozero import LED
from time import sleep 

app = Flask(__name__)
led = LED(17)

@app.route('/')
def index():
        return jsonify(
                {
                        "message":"hello"
                }
        )


@app.route('/on')
def set_led_on():
    if not led.is_lit:
        led.on()
        content = {
                "message":"Led On"
                }
        return jsonify(content)
    else:
        content = {
                "message":"error led is already active"
                }
        return make_response(content,400) 

@app.route('/off')
def set_led_off():
    if led.is_lit:
        led.off()
        content = {
                "message":"Led Off"
                }
        return jsonify(content)
    else:
        content = {
                "message":"error led is not active"
                }
        return make_response(content,400) 

@app.route('/toggle')
def toggle_led():
    led.toggle()
    content = {
            "message":"Led Toggled"
            }
    return jsonify(content)
 


if __name__ == '__main__':
   app.run(host='0.0.0.0',debug=True)
