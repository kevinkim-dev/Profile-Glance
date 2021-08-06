import axios from 'axios';
import store from './store/index'
let baseURL = ''
if (store.getters.DEVELOPMODE)
  baseURL = 'http://localhost:8888/';
else
  baseURL = 'http://52.79.113.173:8888/';

export default axios.create({
  baseURL: baseURL,
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
});
