import './App.scss';
import { Routes, Route } from 'react-router-dom';
import List from './pages/List';
import Create from './pages/Create';
import Update from './pages/Update';
import NotFound from './pages/NotFound';

function App() {
  return (
    <Routes>
      <Route path="/" element={<List />} />
      <Route path="/create" element={<Create />} />
      <Route path="/update/:id" element={<Update />} />
      <Route path='*' element={<NotFound />} />
    </Routes>
  );
}

export default App;
