package com.ljl.community.service;

import com.ljl.community.dto.NotificationDTO;
import com.ljl.community.enums.NotificationStatusEnum;
import com.ljl.community.enums.NotificationTypeEnum;
import com.ljl.community.expcetion.CustomizeErrorCode;
import com.ljl.community.expcetion.CustomizeException;
import com.ljl.community.mapper.NotificationMapper;
import com.ljl.community.model.Notification;
import com.ljl.community.model.NotificationExample;
import com.ljl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azz-ll on 2020/3/4
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    public List<NotificationDTO> list(Integer userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExample(example);
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }

    public NotificationDTO read(Integer id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification==null){
            throw  new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(notification.getReceiver()!=user.getId()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }


    public Long unreadCount(Integer userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(example);
    }
}
