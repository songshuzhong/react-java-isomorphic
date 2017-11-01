const Koa = require( 'koa' );

const router = require( './router' );
const log = require( '../config/log4' );
const app = module.exports = new Koa();

app.use( router.routes() );

app.use( async( ctx, next ) => {
  const start = new Date();
  let ms;
  try {
    await next();
    ms = new Date() - start;
    log.i( ctx, ms );
  } catch ( error ) {
    ms = new Date() - start;
    log.e( ctx, error, ms )
  }

  console.log( `${ ctx.method } ${ ctx.url } - ${ ms } ms` );
} );

if ( !module.parent ) app.listen( 3000 );