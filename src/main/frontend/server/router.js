const Router = require( 'koa-router' );

const problemController = require('./controllers/problem');

const getRouter = () => {
  const router = new Router();
  
  // restful API
  router.get( '/rest/api',problemController.getCommonProblem);

  return router;
};

module.exports = getRouter();