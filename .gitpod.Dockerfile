FROM gitpod/workspace-python

RUN pyenv install 3.9 \
    && pyenv global 3.9

USER gitpod
RUN /bin/bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
    && sdk install java 17.0.8-oracle < /dev/null \
    && sdk flush archives \
    && sdk flush temp"

FROM gitpod/workspace-mysql