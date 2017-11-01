import React, { Component } from 'react';

import { Link } from 'react-router-dom';
/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc 首页
 */
class Home extends Component {
  constructor( props ) {
    super( props );
    this.state = { data: '' };
  }

  componentDidMount() {
    fetch( '/rest/api', { credentials: 'same-origin', method: 'get',
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      } } )
        .then( ( res ) => res.json() )
        .then ( ( data ) => this.setState( { data } ) );
  }

  render() {
    return (
      <div>
        { JSON.stringify( this.state.data ) }
      <ul>
        <li><Link to="/app">app</Link></li>
        <li><Link to="/about">about</Link></li>
        { this.props.children }
      </ul>
      </div>
    );
  }
}

export default Home;