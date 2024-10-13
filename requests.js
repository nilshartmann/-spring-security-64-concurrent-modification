
const user = 'user';
const password = 'secret';
const credentials = btoa(`${user}:${password}`);
const headers = {
  'Authorization': `Basic ${credentials}`
};

getHello("hello")
getHello("goodbye")



function getHello(path) {
  fetch("http://localhost:8080/" + path, {
    headers,
  })
    .then((res) => {
      console.log(path, res.status);
      return res.text();
    })
    .then((r) => console.log(path, r));
}