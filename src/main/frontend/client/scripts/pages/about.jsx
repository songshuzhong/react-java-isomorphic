import React, { Component } from 'react';
import { Link } from 'react-router-dom';

/**
 *@author sshuzhong
 *@mailTo <a href="mailto:songshuzhong@bonc.com.cn">Song ShuZhong</a>
 *@Date 2017/08/10
 *@desc 首页
 */
class About extends Component {
  constructor( props ) {
    super( props );
    this.state = { data: '' };
  }

  componentDidMount() {
    fetch( '/rest/api', { credentials: 'same-origin', method: 'post' } )
        .then( ( res ) => res.json() )
        .then ( ( data ) => this.setState( data ) );
  }

  render() {
    return (
      <div>
        { JSON.stringify( this.state.data ) }
        <li><Link to="/app">app</Link></li>
      </div>
    );
  }
}

export default About;