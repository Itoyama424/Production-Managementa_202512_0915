package com.production.management.service.manufacturing_order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.entity.ManufactureOrderEntity;
import com.production.management.form.manufacture_order.ManufactureOrderForm;
import com.production.management.mapper.entity.ManufactureOrderEntityMapper;

@Service  
public class ManufacturingOrderService {
  
  @Autowired
  private ManufactureOrderEntityMapper manufactureOrderMapper;
  
  /**
   * 製造指示登録処理
   * @param form
   */
  public void registerManufactureOrder(ManufactureOrderForm  form) {
      
      // フォームの内容をエンティティにコピーする
      ManufactureOrderEntity entity = new ManufactureOrderEntity();
      BeanUtils.copyProperties(form, entity);
      
      // 製造指示番号を生成する
      Long moSeq = manufactureOrderMapper.getNextManufactureOrderSeq();      
      String moNo = "MO" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "-" + String.format("%03d", moSeq);
      entity.setMoNo(moNo);
      
      manufactureOrderMapper.insertManufactureOrder(entity);
      
  }
}
