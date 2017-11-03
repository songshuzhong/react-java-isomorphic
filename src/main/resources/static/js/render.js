/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/11/03
 *@desc render
 */
function render( model, context ) {

    var state = JSON.parse( model );
    var ctx = JSON.parse( context );
    console.log(ctx);

    return EPMUIApp.ssr( state, ctx );
}