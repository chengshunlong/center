package com.zhbit.bean;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-11-11:27
 */
public class ShoppingCart {

    Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();


    /**
     * 添加
     * @param cartItem
     */
    public void  addItem(CartItem cartItem){
        //操作表单项
        CartItem item = items.get(cartItem.getEid());
        if(item == null){
            items.put(cartItem.getEid(),cartItem);
        } else {
            //数量为 购物车中的数量 + 新添加进的数量
            item.setEqpAmount(item.getEqpAmount() + cartItem.getEqpAmount());
            //总价为数量 * 单价
            item.setTotalPrice(item.getEqpAmount() * item.getEqpPrice());
        }
    }

    /**
     * 删除
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改数量
     * @param id
     * @param count
     */
    public void updateAmount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setEqpAmount(count);
            cartItem.setTotalPrice(cartItem.getEqpAmount() * cartItem.getEqpPrice());
        }
    }

    /**
     * 购物车总价计算
     * @return
     */
    public Integer getTotalPrice(){
        Integer totalPrice = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice += entry.getValue().getEqpPrice() * entry.getValue().getEqpAmount();
        }
        return totalPrice;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
