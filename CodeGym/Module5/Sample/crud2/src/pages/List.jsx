import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import { useNavigate, Link } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';

function List(props) {
    const navigate = useNavigate();


    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div>
                        <Link to="/create" className='btn add'>Thêm mới</Link>
                        <h2>Danh sách</h2>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default List;