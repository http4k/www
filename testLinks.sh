
cd src/website/public
docker run -v $(pwd):/test --rm wjdp/htmltest . -l 3 -s