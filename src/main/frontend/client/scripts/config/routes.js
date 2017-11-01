import Home from '../pages/home';
import About from '../pages/about';
import App from '../pages/app';
import redirectRoutes from './redirect-routes';

const routes = [
  {
    path: '/',
    exact: true,
    component: Home
  },
  {
    path: '/home',
    exact: true,
    component: Home
  },
  {
    path: '/about',
    component: About
  },
  {
    path: '/app',
    component: App
  }
];


routes.push.apply( routes, redirectRoutes );

export default routes;