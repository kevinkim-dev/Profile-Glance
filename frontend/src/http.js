import axios from 'axios';

export default axios.create({
  baseURL: 'http://52.79.113.173:8888/',
  // baseURL: "http://localhost:8888/",
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
});
