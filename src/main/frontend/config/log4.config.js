const log4 = require( 'log4js' );
const path = require( 'path' );
const fs = require( 'fs' );
const basePath = path.resolve( __dirname, '../log' );

const errorPath = basePath + '/errors/';
const resPath = basePath + '/responses/';

const errorFilename = errorPath + '/error';
const resFilename = resPath + '/response';

const confirmPath = ( pathStr ) => {
  if ( !fs.existsSync( pathStr ) ) {
    fs.mkdirSync( pathStr );
    console.log( 'createPath' + pathStr );
  }
};

log4.configure({
  appenders:[
    {
      category:'console',
      type:'console'
    },
    {
      category:'errorLog',
      type:'dateFile',
      filename:errorFilename,
      alwaysIncludePattern:true,
      pattern:'-yyyy-MM-dd-hh.log'
    },
    {
      category:'responseLog',
      type: 'dateFile',
      filename: resFilename,
      alwaysIncludePattern:true,
      pattern: "-yyyy-MM-dd-hh.log"
    }
  ],
  replaceConsole:true
});

if ( basePath ) {
  confirmPath( basePath );
  confirmPath( errorPath );
  confirmPath( resPath );
}

module.exports = log4;