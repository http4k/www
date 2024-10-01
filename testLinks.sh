
cd src/website/public
cp ../../../htmltest.yml $(pwd)
docker run -v $(pwd):/test --rm wjdp/htmltest . -l 3 | grep -v svg | grep -v js | grep -v png
cd -