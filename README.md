# 北航面向对象设计与构造 OO 2025

## OOPre 部分

### 题目背景

用 Java 迭代开发一个冒险者游戏。前后实现了**不同种类的药水瓶、不同种类的武器、碎片兑换机制以及背包系统**，之后加入了**战斗功能、雇佣机制**，最后加入**秘境工厂副本**。

**一句话课程目标**：掌握 Java 基础语法。

### 操作要求

1. 加入一个需要管理的冒险者（新加入的冒险者不拥有任何药水瓶、装备和碎片，并且初始体力为 500，初始攻击力为 1，初始防御力为 0）
2. 给某个冒险者增加一个药水瓶
3. 给某个冒险者增加一个装备
4. 给某个冒险者的某个装备提升一点耐久度
5. 删除某个冒险者的某个物品
6. 冒险者尝试携带他拥有的某个物品
7. 冒险者使用某个药水瓶
8. 冒险者获得一个碎片
9. 冒险者尝试兑换一次福利
10. 冒险者尝试进行一次战斗
11. 冒险者雇佣另一位雇佣者
12. 冒险者进入“秘境工厂探险”副本

### 输入输出格式
第一行一个整数 n，表示操作的个数。

接下来的 n 行，每行一个形如 {type} {attribute} 的操作，{type} 和 {attribute} 间、
若干个 {attribute} 间使用若干个空格分割，操作输入形式及其含义如下

|type	|attribute	|意义	|输出（每条对应占一行）|
|-|-|-|-|
|1|	{adv_id} {name}	|加入一个 ID 为 {adv_id}、名字为 {name} 的冒险者	|无|
|2|	{adv_id} {bot_id} {name} {capacity} {type} {CE}	|给 ID 为 {adv_id} 的冒险者增加一个药水瓶，药水瓶的 ID、名字、容量、类型、战斗力分别为 {bot_id}、{name}、{capacity}、{type}、{CE}。特别地，当type为 HpBottle 时，我们保证输入的 CE 为 0|无|
|3|	{adv_id} {equ_id} {name} {durability} {type} {CE}	|给 ID 为 {adv_id} 的冒险者增加一个装备，装备的 ID、名字、耐久度、类型、战斗力分别为 {equ_id}、{name}、{durability}、{type}、{CE}|	无|
|4|	{adv_id} {equ_id}|	将 ID 为 {adv_id} 的冒险者的 id 为 {equ_id} 的装备提升一点耐久度	|{一个字符串} {一个整数}，字符串为装备的 name，整数为装备提升耐久后的耐久度|
|5|	{adv_id} {id}	|将 ID 为{adv_id}的冒险者的 id 为 {id} 的物品删除	|{一个字符串A} {一个字符串B} {一个整数C}，字符串 A 为物品的类名（答案只能在以下类名中挑选其一： HpBottle、AtkBottle、DefBottle、Axe、Sword、Blade），字符串 B 为被删除的物品的name。若物品为药水瓶：整数 C 为被删除的药水瓶的容量；若物品为装备：整数 C 为被删除的装备的耐久度|
|6|	{adv_id} {id}	|ID 为 {adv_id} 的冒险者尝试携带 id 为 {id} 的物品	|无|
|7|	{adv_id} {bot_id}|	ID 为 {adv_id} 的冒险者尝试使用他拥有的 id 为{bot_id}的药水瓶|	成功：{一个字符串} {一个整数A} {一个整数B} {一个整数C}，字符串为该冒险者的 name，整数 A 为该冒险者使用该药水瓶后的体力值，整数 B 为该冒险者使用该药水瓶后的攻击力值，整数 C 为该冒险者使用该药水瓶后的防御力值；失败： {adv_name} fail to use {name}，adv_name 为 ID 为 adv_id 的冒险者的 name， name 为 ID 为 bot_id的药水瓶的 name)|
|8|	{adv_id} {frag_id} {name}|	ID 为{adv_id}的冒险者获得一个 id 、名字分别为{frag_id}、{name}的碎片	|无|
|9|	{adv_id} {name} {welfare_id}	|ID 为{adv_id}的冒险者尝试使用其拥有的名字为name的碎片兑换一次福利，福利标识码为welfare_id	|成功：a) {一个字符串} {一个整数}，字符串为对应药水瓶的name，整数为对应药水瓶的容量；b) {一个字符串} {一个整数}，字符串为对应装备的name，整数为对应装备的新耐久度；c) Congratulations! HpBottle {name} acquired ，name为获得的药水瓶的name。失败：{一个整数}: Not enough fragments collected yet ， 整数为该冒险者拥有的名字为frag_name的碎片的数目|
|10|	{adv_id}  {name} {attack_type} {k} {adv_id_1} {adv_id_2} … {adv_id_k}|	ID 为 {adv_id} 的冒险者尝试使用名字为 {name}的装备与k个冒险者进行一次战斗类型为{attack_type}的战斗（k个冒险者的 ID 分别为 adv_id_1、adv_id_2、…、adv_id_k）|	normal成功：输出k行，第m行的格式为{ID 为 adv_id_m的冒险者的name} {ID 为 adv_id_m的冒险者受到攻击后的体力值}；chain成功：{一个整数}，整数为本次战斗中所有冒险者失去的体力值的总和。战斗失败：Adventurer {adv_id} defeated，其中{adv_id}为输入中的攻击者的 ID|
|11|	{adv_id_1} {adv_id_2}|	ID 为 {adv_id_1}的冒险者雇佣 ID 为 {adv_id_2}的冒险者	|无|
|12|	{adv_id}|	ID 为 {adv_id}的冒险者挑战“秘境探险工厂”副本	|按宝物获得的顺序从早到晚输出 k 行，每行为{一个字符串}，字符串为宝物信息。如果冒险者在这次副本探险内没有获得宝物，则不输出。|
