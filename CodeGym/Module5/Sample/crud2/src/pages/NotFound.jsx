import React from 'react';
import imgNotFound from '../assets/imgs/image.png'
import { Link } from 'react-router-dom';
function NotFound(props) {
    return (
        <div className='not-found'>
            <img src={imgNotFound} alt="NotFound" />
            <Link to="/">Back List</Link>
        </div>
    );
}

export default NotFound;
