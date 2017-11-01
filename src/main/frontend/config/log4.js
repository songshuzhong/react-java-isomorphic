const log4 = require( './log4.config' );

const errorLog = log4.getLogger( 'errorLog' );
const resLog = log4.getLogger( 'responseLog' );

const formatRes = ( ctx, resTime ) => {
  let logText = new String();

  logText += "\n" + "******************* response log start **************************" + "\n";
  logText += formatReqLog( ctx.request, resTime );
  logText += "request ip: " + ctx.request.host + "\n";
  logText += "response status: " + ctx.status + "\n";
  /*logText += "response body: " + "\n" + JSON.stringify( ctx.body ) + "\n";*/
  logText += "******************* response log end **************************" + "\n";

  return logText;
};

const formatError = ( ctx, err, resTime ) => {
  let logText = new String();

  logText += "\n" + "******************* error log start **************************" + "\n";
  logText += formatReqLog( ctx.request, resTime );
  logText += "err name: " + err.name + "\n";
  logText += "err message: " + err.message + "\n";
  logText += "err stack: " + err.stack + "\n";
  logText += "******************* error log end **************************" + "\n";

  return logText;
};

const formatReqLog = ( req, resTime ) => {
  let logText = new String();

  let method = req.method;

  logText += "request method: " + method + "\n";
  logText += "request originalUrl: " + req.originalUrl + "\n";
  logText += "request client ip: " + req.ip + "\n";

  if ( method === "GET" ) {
    logText += "request query: " + JSON.stringify( req.query ) + "\n";
  } else {
    logText += "request body: " + "\n" + JSON.stringify( req.body ) + "\n";
  }

  logText += "response time: " + resTime + "\n";

  return logText;
};

const log = {};

log.i = ( ctx, resTime ) => {
  if ( ctx ) {
    resLog.info( formatRes( ctx, resTime ) );
  }
};

log.e = ( ctx, error, resTime ) => {
  if ( ctx&&error ) {
    errorLog.error( formatError( ctx, error, resTime ) );
  }
};

module.exports = log;