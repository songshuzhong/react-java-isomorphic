const defaultWebpackConfig = require( 'epm-ui-boot-dev/config/webpack.config' );
const babelrc = require( 'epm-ui-boot-dev/config/babelrc' );
const ExtractTextPlugin = require( 'extract-text-webpack-plugin' );
const autoprefixer = require( 'autoprefixer' );

module.exports = ( env ) => {
  let config = defaultWebpackConfig( env );
  
  config.module.rules[0].oneOf.unshift( {
    test: /\.less$/,
    use: ExtractTextPlugin.extract( {
      fallback: require.resolve( 'style-loader' ),
      use: [
        {
          loader: require.resolve( 'css-loader' ),
          options: {
            importLoaders: 1,
            minimize: true,
            sourceMap: true,
          },
        },
        {
          loader: require.resolve( 'postcss-loader' ),
          options: {
            plugins: () => [
              require( 'postcss-flexbugs-fixes' ),
              autoprefixer( {
                browsers: [
                  '>1%',
                  'last 4 versions',
                  'Firefox ESR',
                  'not ie < 9', // React doesn't support IE8 anyway
                ],
                flexbox: 'no-2009',
              } ),
            ],
          },
        },
        {
          loader: require.resolve( 'less-loader' )
        },
      ],
    } )
  } );
  
  return config;
};