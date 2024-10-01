
cd src/website/public
cp -R ../../../htmltest.yml .
docker run -v $(pwd):/test --rm wjdp/htmltest . -l 1 -s
cd -