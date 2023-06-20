import React from "react";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import Root from "components/Root";
import { StoreProvider } from "./StoreContext";

function App() {
  return <StoreProvider><Root /></StoreProvider>;
}

export default App;