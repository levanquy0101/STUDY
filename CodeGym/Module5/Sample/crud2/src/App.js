import './App.scss';
import {Routes, Route} from 'react-router-dom';
import List from './pages/List';
import NotFound from './pages/NotFound';
import Create from './pages/Create';
import Update from './pages/Update';
import View from "./pages/View";

function App() {
  return (
      <Routes>
        <Route path="/" element={<List />} />
        <Route path="/create" element={<Create />} />
        <Route path="/update" element={<Update />} />
        <Route path="/view/:id" element={<View />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
  );
}

export default App;
