/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/30
 *@desc render
 */
function render( model, context ) {

    var state = JSON.parse( model );
    var ctx = JSON.parse( context );

    return EPMUIApp.ssr( state, ctx );
}