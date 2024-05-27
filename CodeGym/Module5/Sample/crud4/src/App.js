import './App.scss';
import {BrowserRouter as Router ,Routes, Route} from 'react-router-dom';
import List from './pages/List';
import NotFound from './pages/NotFound';
import Create from './pages/Create';
import Update from './pages/Update';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<List />} />
        <Route path="/create" element={<Create />} />
        <Route path="/update" element={<Update />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
