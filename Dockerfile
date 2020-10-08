FROM ubuntu

COPY build/bin/test-http4k-graal /

CMD ["/test-http4k-graal"]
