import cv2
import sys
import datetime
import time
import requests
import numpy as np
from os import path
from imutils import grab_contours
from pytesseract import image_to_string

# TODO: remove prints and add logging mechanism

# TODO: improve this function for checking right folder is created.
def is_upload_folder_created(folder_name="uploads/"):
    print("Uploads folder succesfully created")
    return True

def get_timestamp():
    return datetime.datetime.now().strftime('%d_%m_%Y_%H_%M_%S')

def convert_imagefile_to_string(image):
    print("Image prediction is running")
    return image_to_string(image, lang="eng")

def send_check_plate_request(plate_text):
    data = {'plate': plate_text}
    response = requests.post(f"http://localhost:9080/checkplate",json=data)
    if response.status_code == requests.codes.ok:
        print(f"plate {response.text}")
        return True
    else:
        print(f"plate {response.text}")
        return False

def send_signal_to_door(status):
    try:
        response = requests.get(f"http://192.168.43.88:5000/{status}")
    except:
        cv2.destroyAllWindows()
        raise Exception("while sending request something happen")
    else:
        if response.status_code == requests.codes.ok:
            print(f"Request sended: {response.text}")
            return True
        else:
            print(f"Request sended: {response.text}")
            return False

def process_image(img):
    plate_area = None

    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    filtered = cv2.bilateralFilter(gray_img, 6, 250, 250)
    edged = cv2.Canny(filtered, 30, 200)

    #Finding All Contour
    contours = cv2.findContours(edged, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    sorted_contours = sorted(grab_contours(contours), key=cv2.contourArea, reverse=True)[:10]

    #Finding Corners Of Plate Contour
    for c in sorted_contours:
        epsilon = 0.06 * cv2.arcLength(c, True)  #NOTE: 0.06 better than 0.018.
        approx = cv2.approxPolyDP(c, epsilon, True)
        if len(approx) == 4:
            plate_area = approx
            break

    #Mask: Filling the image with black pixels excluding the "Plate" plane 
    mask = np.zeros(gray_img.shape, np.uint8)
    cv2.drawContours(mask, [plate_area], 0, (255, 255, 255), -1)
    new_img = cv2.bitwise_and(img, img, mask=mask)

    #Clipping: "Plate" Plane Clipping From Image
    #We have just plate area
    x, y = np.where(mask == 255)
    topx, topy = np.min(x), np.min(y)
    bottomx, bottomy = np.max(x), np.max(y)

    return gray_img[topx:bottomx + 1, topy:bottomy + 1]




def save_image(image,file_path=None):
    if file_path is not None:
        cv2.imwrite(file_path,image)
        print(f"Image saved -{file_path}- in this directory")
    else:
        raise Exception("While exporting, Image file name can not be empty")

# TODO: try to divide this function smaller units
def start_video_process(video_device=0, upload_folder_name="uploads"):

    video_capture_device = cv2.VideoCapture(video_device)
    image_precess_toggle = True
    working_period = datetime.timedelta(seconds=10)
    process_activated_time = datetime.datetime.now()

    while video_capture_device.isOpened():

        is_readable, video_frame = video_capture_device.read()
        key = cv2.waitKey(1)
        
        if not is_readable:
            cv2.destroyAllWindows()
            raise Exception("Camera/Video source is not valid")
            sys.exit(2)
        
        if image_precess_toggle:
            print("Waiting new car")
            image_filename = get_timestamp()
            car_frame_name = f"{upload_folder_name}/{image_filename}.png"
            processed_car_frame_name = f"{upload_folder_name}/{image_filename}_processed.png"

            processed_car_frame = process_image(video_frame)
            if len(processed_car_frame) > 0:
                print("New car detected")
                plate_text = convert_imagefile_to_string(processed_car_frame)

                save_image(video_frame,car_frame_name)
                save_image(processed_car_frame,processed_car_frame_name)

                if len(plate_text) > 0:
                    print("Car plate predicted --> ", plate_text)
                    request_status = send_check_plate_request(plate_text)
                    if request_status:
                        if send_signal_to_door("on"):
                            process_activated_time = datetime.datetime.now()
                            image_precess_toggle = False

                        print(image_precess_toggle)
                else:
                    print("plate can not converted succesfully")            


        if(datetime.datetime.now() - process_activated_time > working_period) and not image_precess_toggle:
            image_precess_toggle = True
            send_signal_to_door("off")
            # print(image_precess_toggle)

        if key == ord('q'):
            break

        cv2.imshow('video', video_frame)

    video_capture_device.release()
    cv2.destroyAllWindows()


def main():
    if is_upload_folder_created():
        #start_video_process("https://192.168.43.1:8080/video")
        start_video_process()
    else:
        sys.exit(1)
    
if __name__ == "__main__":
    main()