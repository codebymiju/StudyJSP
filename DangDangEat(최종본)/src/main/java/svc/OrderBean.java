package svc;

public class OrderBean {
/*
 CREATE TABLE coupon(
   cp_code VARCHAR(30)  primary key, -- 할인코드(PK)
   cp_name VARCHAR(30), -- 할인코드명
   cp_target VARCHAR(15), -- 타켓(birth, new_member, event )
   cp_discount_value INT, -- 할인율 / (단위:%)
   cp_startdate VARCHAR(10), -- 쿠폰시작일(YYYY-MM-DD): birth, new_member는 null
   cp_period INT, -- 기간/ (단위:일)
   cp_min_price INT, -- 최소 구매액
   cp_max_discount INT, -- 최대 할인액
   cp_status INT -- 쿠폰상태(0 삭제 , 1 존재)
 );
 
 CREATE TABLE member_coupon (
	member_id varchar(30) ,
	cp_code varchar(100),
	mc_created_date  date,
	mc_used  varchar(1) , -- 사용여부
 	CONSTRAINT FOREIGN KEY ( member_id )
        REFERENCES members ( member_id ) ,
 	CONSTRAINT FOREIGN KEY ( cp_code )
        REFERENCES coupon ( cp_code ) 
);

CREATE TABLE PAYMENTS (
	pay_number varchar(20) primary key,
    order_code VARCHAR(17),
    pay_amount INT not null,
    pay_status VARCHAR(10) not null,
    FOREIGN KEY(order_code) references orders(order_code)
);
CREATE TABLE orders(
	order_code VARCHAR(17) Primary key, 
	member_id VARCHAR(20),
	order_name VARCHAR(20) Not null, 
	order_postcode VARCHAR(45),
	order_address1 VARCHAR(100) Not null,
	order_address2 VARCHAR(100) Not null,
	order_mobile VARCHAR(11) Not null,
	order_comment VARCHAR(50),
	order_status INT not null,
	order_date DATE,
	FOREIGN KEY(member_id) references member(member_id),
);

CREATE TABLE order_product(
	order_code VARCHAR(17) NOT NULL,
	pro_code VARCHAR(20) NOT NULL,
	order_stock INT NOT NULL,
	FOREIGN KEY(order_code) references orders(order_code),
	FOREIGN KEY(pro_code) references product(pro_code)
);


 */
	
	
	
}
