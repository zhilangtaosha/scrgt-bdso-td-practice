#!/bin/bash

cat << 'EOF' > ./requirements.txt
awscli
boto3
requests
wikipedia
imdbpy
nltk
face_recognition_models
Click>=6.0
dlib>=19.3.0
Pillow
scipy>=0.17.0
tensorflow
tensorflow_hub
elasticsearch==7.0.4
scikit-learn==0.21.2
pickle-mixin
EOF

cat << 'EOF' > ./bootstrap.sh
#!/bin/bash

apt-get -y update

apt-get install -y --fix-missing \
    build-essential \
    cmake \
    gfortran \
    git \
    wget \
    curl \
    graphicsmagick \
    libgraphicsmagick1-dev \
    libatlas-base-dev \
    libavcodec-dev \
    libavformat-dev \
    libgtk2.0-dev \
    libjpeg-dev \
    liblapack-dev \
    libswscale-dev \
    pkg-config \
    python3-dev \
    python3-numpy \
    software-properties-common \
    zip \
    git \
    jq \
    && apt-get clean && rm -rf /tmp/* /var/tmp/*

cd ~ && \
    mkdir -p dlib && \
    git clone -b 'v19.9' --single-branch https://github.com/davisking/dlib.git dlib/ && \
    cd  dlib/ && \
    python3 setup.py install --yes USE_AVX_INSTRUCTIONS

pip install -r /etc/jupyter/requirements.txt
EOF

sudo docker cp ./requirements.txt jupyterhub:/etc/jupyter/requirements.txt
sudo docker cp ./bootstrap.sh jupyterhub:/etc/jupyter/bootstrap.sh
sudo docker exec jupyterhub chmod u+x /etc/jupyter/bootstrap.sh
sudo docker exec jupyterhub bash /etc/jupyter/bootstrap.sh
