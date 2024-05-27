import React from 'react';
import styles from './Sidebar.module.scss';
import { Link } from 'react-router-dom';

function Sidebar(props) {
    return (
        <div className={styles.sidebar}>
            <div className={styles.logo}>
                <p className={styles.text}>Logo</p>
            </div>
            <div className={styles.management}>
                <h3>Management</h3>
                <ul>
                    <li><Link to="/">Danh sách</Link></li>
                    <li><Link to="/create">Thêm mới</Link></li>
                </ul>
            </div>
        </div>
    );
}

export default Sidebar;