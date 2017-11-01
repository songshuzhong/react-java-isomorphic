import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import logo from '../../media/logo.svg';
import '../../styles/app.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React!!!!</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <Link to="/about">about</Link>
      </div>
    );
  }
}

export { App };
export default App;