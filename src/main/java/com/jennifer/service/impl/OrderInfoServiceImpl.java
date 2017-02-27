package com.jennifer.service.impl;

import com.jennifer.dao.OrderInfoDao;
import com.jennifer.entity.OrderInfo;
import com.jennifer.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * OrderInfoService implementation
 */
@Service("orderInfoService")
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {
    private static final Logger log = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
    private OrderInfoDao orderInfoDao;

    @Autowired
    public OrderInfoServiceImpl(OrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    @Override
    public List<OrderInfo> findAllOrders() {
        return orderInfoDao.findAll();
    }

    @Override
    public OrderInfo findById(int id) {
        return orderInfoDao.findById(id);
    }
}

