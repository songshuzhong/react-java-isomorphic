/**
 * Created by Administrator on 2017/10/29.
 */
function render( model, context ) {

    var state = JSON.parse( model );
    var ctx = JSON.parse( context );

    var rendered = EPMUIApp.ssr( state, ctx );

    return rendered;
}