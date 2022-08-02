# To-do list
* หัวข้อจะมี "(`x` คะแนน)" ขึ้นต้น
* หัวข้อใหญ่เป็นตัวหนา แปลว่า**ต้องทำ**ทั้งหัวข้อและ child
  * บางหัวข้อเป็นตัวหนา แต่ child ขึ้นต้นด้วย `(extra)` แปลว่าไม่จำเป็น
* หัวข้อใหญ่ขึ้นต้นด้วย `(extra)` แปลว่าไม่จำเป็น
## user Interface
## 12. ความสวยงามและประสบการณ์ของผู้ใช้งาน
  - [ ] *12.1 (5 คะแนน) ใช้โทนสีและองค์ประกอบต่าง ๆ ของ GUI ที่ดูได้ชัดเจน น่าใช้ ไม่แสบตา ไม่ลวงตา โดยลองนึกถึงว่าหากเราทำโปรแกรมนี้ให้คนทั่วไปใช้จริง ควรแสดงองค์ประกอบเหล่านี้อย่างไร*
  - 12.2 (caution) โปรดตรวจสอบความถูกต้องของข้อความที่ปรากฏในส่วนต่าง ๆ ของโปรแกรม หรือเอกสารประกอบ หากพบว่ามีคำผิด หักคำที่ผิดจุดละ 5 คะแนน
  - [ ] **12.3 (10 คะแนน) Graphic User Interface (GUI)**
    - ควรมีรูปแบบที่เข้าใจง่าย มีการแสดงข้อมูลที่ชัดเจน
    - [ ] มี Label กำกับข้อมูลที่ชัดเจน เพื่อให้เข้าใจว่าค่าที่แสดงนั้นคือค่าอะไร
    - มีการแสดงองค์ประกอบต่าง ๆ ในขนาดที่เหมาะสม ไม่เล็กหรือไม่ใหญ่จนเกินไป
    - [ ] ขนาดหน้าจอของโปรแกรมต้องมีความสูงไม่เกิน 1024 pixel
  - [ ] 12.4 (extra 5 คะแนน) GUI มี effect ที่น่าสนใจ
    - เมื่อมี action ต่าง ๆ เช่น มีการ highlight button เมื่อคลิกปุ่ม 
    - มี effect การ transition เมื่อเปลี่ยน scene เป็นต้น
  - [ ] 12.5 (extra 10 คะแนน) Theme
    - [ ] ผู้ใช้โปรแกรมสามารถปรับเปลี่ยน Theme ของ Application ได้
      - เปลี่ยนโทนสีหรือ
      - เปลี่ยนขนาดอักษรหรือ
      - เปลี่ยนฟอนต์ของตัวอักษรได้
    - [ ] โดยจะต้องเปลี่ยนให้สอดคล้องกันทุก Scene
## ทั่วไป
- [ ] **13. (5 คะแนน) ต้องมีส่วนของ GUI ที่แสดงถึงข้อมูลต่อไปนี้ โดยอาจเข้าถึงข้อมูลนี้ผ่านเมนู หรือมีปุ่มกดที่หน้าแรกข้อมูลนิสิตผู้จัดทำโปรแกรม**
  - [ ] รูปที่แสดงหน้าตา (หน้ายิ้ม) ที่ชัดเจน ชื่อ นามสกุล ชื่อเล่น รหัสนิสิต
  - [ ] ข้อมูลคำสั่ง หรือคำแนะนำ ในการใช้โปรแกรมที่นิสิตสร้างขึ้นมา

## 14. ระบบของผู้ดูแลระบบ
  - [ ] **ระบบส่วนนี้ถูกจำกัดสิทธิ์สำหรับผู้ดูแลระบบเท่านั้น**
  - [ ] **ผู้ใช้ที่ไม่ใช่ผู้ดูแลระบบต้องไม่สามารถเข้าใช้งานส่วนนี้ได้**
  - [ ] **Application ไม่ต้องมีระบบการสร้างบัญชีสำหรับผู้ดูแลระบบ**
  - [ ] **ให้นิสิตระบุ username และ password ของผู้ดูแลระบบในไฟล์ pdf** (ข้อ 11 หัวข้อ "ตัวอย่างข้อมูลผู้ใช้ระบบ")
  - [ ] 14.1 (5 คะแนน) ผู้ดูแลระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้ รหัสผ่านใหม่ต้องใช้ได้
  - [ ] **14.2 (5 คะแนน) มีหน้าแสดงรายชื่อของผู้ใช้ระบบ** 
    - [ ] แสดงภาพผู้ใช้ username
    - [ ] ชื่อของผู้ใช้ระบบ 
    - [ ] ชื่อร้านค้า (กรณีผู้ใช้ระบบเปิดร้านค้าของตนเอง) 
    - [ ] วันเวลาที่เข้าใช้ล่าสุดของผู้ใช้ระบบคนนั้น 
    - [ ] เรียงลำดับตามวันเวลาที่ผู้ใช้ระบบเข้าใช้งานล่าสุดก่อน
  - [ ] *14.3 (10 คะแนน) หน้าแสดงรายการของรายงานความไม่เหมาะสมของผู้ใช้ระบบ*
    - [ ] มีหน้าแสดงรายการของรายงานความไม่เหมาะสมของผู้ใช้ระบบ
    - (เช่น เนื้อหาไม่เหมาะสม ความคิดเห็นไม่เหมาะสม ข่าวปลอม เนื้อหาล่อแหลม เนื้อหาอันตรายมีความรุนแรง ฯลฯ)
    - [ ] ผู้ดูแลระบบสามารถระงับสิทธิ์การเข้าใช้งานของผู้ใช้ระบบได้ 
    - [ ] ผู้ใช้ระบบที่ถูกระงับสิทธิ์จะไม่สามารถเข้าใช้งานโปรแกรมได้ มีข้อความแจ้งเตือนการถูกระงับสิทธิ์ 
    - [ ]ผู้ดูแลระบบสามารถทราบถึงจำนวนครั้งในการพยายามเข้าใช้โปรแกรมในขณะที่ผู้ใช้ระบบถูกระงับสิทธิ์ 
    - [ ] ผู้ดูแลระบบสามารถคืนสิทธิ์การเข้าใช้งานของผู้ใช้ระบบได้
  - [ ] 14.4 (5 คะแนน) มีหน้าสําหรับสร้างบัญชีผู้ใช้ของเจ้าหน้าที่
    -[ ]  โดยระบุชื่อสําหรับเข้าสู่ระบบ (username) รหัสผ่านยืนยันรหัสผ่านชื่อของเจ้าหน้าที่ภาพของเจ้าหน้าที่และหน่วยงานที่รับผิดชอบโดยมีการตรวจสอบusername จะต้องไม่ซํ้ากับผู้ใช้ระบบที่มีอยู่แล้ว
  - [ ] 14.5 (extra 5 คะแนน) มีเมนูจัดการหน่วยงานของเจ้าหน้าที่
    - [ ] ผู้ดูแลระบบสามารถสร้างหน่วยงานใหม่หรือแก้ไขชื่อหน่วยงานเดิมได้
    - [ ] ผู้ดูแลระบบสามารถกําหนดได้ว่าหน่วยงานอยู่ในความรับผิดชอบของเจ้าหน้าที่คนใด
    - [ ] หากการจัดการหน่วยงานเป็นแบบhard code หรือไม่สามารถเพิ่มหมวดหมู่ใหม่ได้จากในโปรแกรมจะไม่ได้คะแนนextra ส่วนนี้
  - [ ] 14.6.(extra 5 คะแนน) มีเมนูจัดการหมวดหมู่เรื่องร้องเรียน
    - [ ] ผู้ดูแลระบบสามารถสร้างหมวดหมู่เรื่องร้องเรียนได้
    - [ ] หมวดหมู่เรื่องร้องเรียนที่แตกต่างกันมีคุณลักษณะของเรื่องร้องเรียนที่แตกต่างกัน
    - [ ] หากการจัดการหมวดหมู่เรื่องร้องเรียนเป็นแบบhardcode   หรือไม่สามารถเพิ่มหมวดหมู่เรื่องร้องเรียนใหม่ได้จากในโปรแกรมจะไม่ได้คะแนนextra ข้อ14.6 นี้
  - [ ] 14.7 คุณลักษณะของเรื่องร้องเรียนภายในหมวดหมู่
    - [ ] (extra 5 คะแนน) คุณลักษณะที่แตกต่างกันที่จัดการในข้อ 14.6.2 สามารถเพิ่มตัวเลือกของข้อมูลได้โดยแสดงตัวเลือกของข้อมูลสําหรับคุณลักษณะนี้ (ข้อ16.1) แบบdropdown
    - [ ] (extra 15 คะแนน) คุณลักษณะที่แตกต่างกันที่จัดการในข้อ 14.6.2 สามารถเพิ่มคุณลักษณะประเภทที่ต้องการให้upload ภาพโดยการแสดงผล (ข้อ 16.1) จะต้องให้ผู้ใช้ระบบupload รูปภาพได้และต้องแสดงภาพในรายละเอียดของเรื่องร้องเรียนได้ถูกต้อง
    - [ ] จะต้องได้คะแนนextra จากข้อ 14.6 ก่อนและต้องไม่ใช้hard code จึงจะพิจารณาคะแนนextra ข้อ  14.7 นี้  
## 15. การสร้างบัญชีของผู้ใช้ระบบ
  - [ ] **15.1 (5 คะแนน) ระบบลงทะเบียน (registration)**
    - [ ] มีระบบลงทะเบียน (registration)
    - [ ] โดยผู้ใช้ระบบที่ลงทะเบียนนี้จะเป็นผู้ซื้อเท่านั้น
    - ข้อมูลที่ใช้ในการลงทะเบียน ได้แก่
      - ชื่อของผู้ใช้ระบบ
      - ชื่อสำหรับเข้าสู่ระบบ (username)
      - รหัสผ่าน ยืนยันรหัสผ่าน
    - [ ] โดยมีการตรวจสอบ username จะต้องไม่ซ้ำกับผู้ใช้ระบบที่มีอยู่แล้ว
  - [ ] 15.2 (5 คะแนน) ผู้ใช้ระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้
    - [ ] และรหัสผ่านใหม่ต้องใช้ได้
  - [ ] **15.3 (5 คะแนน) ผู้ใช้ระบบ upload รูปภาพ เพื่อใช้เป็นภาพของผู้ใช้ระบบ**
    - [ ] upload ได้
    - [ ] และสามารถเปลี่ยนรูปได้
    - [ ] หากผู้ใช้ระบบยังไม่กำหนดภาพ ระบบจะให้ใช้ภาพ default แทนไปก่อน
## 16. ระบบสำหรับผู้นิสิต  ผู้ใช้ระบบที่ลงทะเบียนตามข้อ 15.1 จะเป็นนิสิตโดยปริยาย
  - [ ] **16.1 (5 คะแนน) มีหน้าแจ้งเรื่องร้องเรียนภายในมหาวิทยาลัย**
    - [ ] หัวเรื่องรายละเอียดเรื่องร้องเรียน
    - [ ] หมวดหมู่เรื่องร้องเรียน
    - [ ] ข้อมูลที่ต้องการในหมวดหมู่เรื่องร้องเรียนนั้น
   - [ ] **16.2  มีหน้ารวมเรื่องร้องเรียนทั้งหมดจากทุกหมวดหมู่**
    - [ ] แยกเรื่องร้องเรียนที่ถูกจัดการแล้วอยู่ระหว่างดําเนินการและยังไม่ถูกจัดการ
      - [ ] **(5 คะแนน) มีส่วนให้ผู้ใช้ระบบเลือกเรียงลําดับเรื่องร้องเรียน**
           **จากเวลาที่แจ้งล่าสุดหรือเก่าสุดขึ้นแสดงก่อน**
      - [ ] ** (5 คะแนน) มีส่วนให้ผู้ใช้ระบบเลือกเรียงลําดับเรื่องร้องเรียน**
            **จากคะแนนโหวตมากที่สุดหรือน้อยที่สุดขึ้นแสดงก่อน**
      - [ ] ผู้จัดทําโปรแกรมจะต้องเป็นผู้เขียนlogic การเรียงลําดับเองจึงจะได้คะแนน
  - [ ] **16.3 ในหน้ารวมเรื่องร้องเรียนทั้งหมด (ข้อ 16.2)       มีส่วนให้ผู้ใช้ระบบเลือกการแสดงผลที่สนใจ**
    - [ ] (5 คะแนน) เลือกการแสดงผลเฉพาะหมวดหมู่เรื่องร้องเรียนที่สนใจ
    โดยยังคงเรียงลําดับการแสดงผลจากข้อ 16.2.1 หรือข้อ 16.2.2 ได้
    - [ ] (5 คะแนน) เลือกการแสดงผลเฉพาะเรื่องร้องเรียนที่มีช่วงคะแนนโหวต
    ที่ผู้ใช้ระบบกําหนดเองมากกว่า1 รูปแบบ
      - [ ] ผู้ใช้เลือกแสดงเรื่องร้องเรียนที่มีมากกว่าx คะแนนโหวต 
      - [ ] ผู้ใช้เลือกแสดงเรื่องร้องเรียนที่มีตั้งแต่m–n คะแนนโหวต
      - [ ] โดยยังคงเรียงลําดับการแสดงผลจากข้อ 16.2.1 หรือข้อ 16.2.2 ได้
    - [ ] **16.4 ผู้ใช้ระบบเข้าดูรายละเอียดของเรื่องร้องเรียนจากหน้ารวมเรื่องร้องเรียนทั้งหมดได้**
      - [ ] **16.4.1.(5 คะแนน) รายละเอียดของเรื่องร้องเรียนจะต้องแสดงข้อมูลครบถ้วน**
      **ตามที่หมวดหมู่ของเรื่องร้องเรียนนั้นกําหนดโดยไม่แสดงคุณสมบัติอื่นที่ไม่เกี่ยวข้องกับหมวดหม** **ของเรื่องร้องเรียนนั้นแสดงคะแนนโหวตของเรื่องร้องเรียนนั้น**
    - [ ] 16.4.2. (5 คะแนน) ในหน้ารายละเอียดของเรื่องร้องเรียนมีส่วนที่ให้ผู้ใช้ระบบแจ้งรายงาน
    ความไม่เหมาะสมของเนื้อหาหรือของผู้ใช้ระบบซึ่งจะไปแสดงผลในข้อ 14.3โดยต้องระบุสิ่งที่รายงาน     (รายงานเนื้อหาหรือรายงานผู้ใช้ระบบ) ประเภทของความไม่เหมาะสมและข้อมูลเพิ่มเติม
        - [ ] ผู้ดูแลระบบสามารถพิจารณาลบเนื้อหาได้หากเป็นการรายงานเนื้อหา
        - [ ] ผู้ดูแลลระบบสามารถพิจารณาระงับสิทธิ์การใช้งานของผู้ใช้ได้หากเป็นการรายงานผู้ใช้
        - [ ] หากไม่มีการรายงานผู้ดูแลระบบจะลบเนื้อหาหรือระงับสิทธิ์การใช้งานของผู้ใช้ไม่ได้
      - [ ] **16.4.3.(5 คะแนน) ในหน้ารายละเอียดของเรื่องร้องเรียนมีส่วนให้ผู้ใช้ระบบสามารถให้คะแนนโหวตได้ 1 คะแนนโหวตต่อครั้ง**
        - [ ] 16.4.3.1.(extra 5 คะแนน) ผู้ใช้ระบบให้คะแนนโหวตในเรื่องร้องเรียนเดิมซํ้าไม่ได้
      - [ ] **16.5 (5 คะแนน)มีหน้ารวมเรื่องร้องเรียนเฉพาะเรื่องร้องเรียนที่ผู้ใช้ระบบที่กําลังlogin อยู่**
        - [ ] เป็นผู้แจ้งเรื่องร้องเรียน
        - [ ] แสดงสถานะการจัดการเรื่องร้องเรียน
        - [ ] สามารถเข้าดูรายละเอียดของเรื่องร้องเรียน
## 17.ระบบสําหรับเจ้าหน้าที่  
  - [ ] 17.1.(5 คะแนน) เจ้าหน้าที่สามารถเปลี่ยนรหัสผ่านของตนเองได้โดยต้องระบุusername และpassword เดิมที่ถูกต้องด้วยและรหัสผ่านใหม่ต้องใช้ได้
   - [ ] 17.2.**มีหน้าจัดการเรื่องร้องเรียนเฉพาะหมวดหมู่ที่ตนเองรับผิดชอบและไม่สามารถจัดการเรื่องร้องเรียนของหมวดหมู่อื่นที่ไม่อยู่ในความรับผิดชอบ**
     - [ ] **17.2.1.(5 คะแนน)หน้าจัดการเรื่องร้องเรียนนี้แสดงรายการเรื่องร้องเรียนทั้งหมดในหมวดหมู่ที่ตนเองรับผิดชอบและแสดงสถานะว่าจัดการไปแล้วหรือยัง**
     - [ ] **17.2.2.(5 คะแนน)สามารถเลือกแสดงรายละเอียดของเรื่องร้องเรียนในหมวดหมู่ที่ตนเองรับผิดชอบในหน้าแสดงรายละเอียดมีส่วนให้จัดการเรื่องร้องเรียนโดยต้องระบุรายละเอียดของวิธีการจัดการและเลือกสถานะได้ว่าอยู่ระหว่างดําเนินการหรือเสร็จสิ้นแล้ว** 
     **สถานะของเรื่องร้องเรียนและรายละเอียดของวิธีการจัดการจะต้องแสดงในหน้ารายละเอียดของเรื่องร้องเรียนให้ผู้ใช้ทั่วไปเห็นได้**
     - [ ] **17.2.3.รายละเอียดของเรื่องร้องเรียนต้องไม่แสดงชื่อของผู้ร้องเรียนแต่แสดงหน่วยงานของเจ้าหน้าที่ที่จัดการเรื่องร้องเรียนโดยไม่แสดงชื่อเจ้าหน้าที่ให้ผู้ใช้ทั่วไปเห็น**
    *5 คะแนน) แสดงชื่อเจ้าหน้าที่ที่จัดการเรื่องร้องเรียนให้เจ้าหน้าที่ในหน่วยงานเดียวกันเห็น18.โปรแกรมต้องบันทึกค่าของข้อมูลต่างๆในรูปแบบของไฟล์csv (comma-separated-values) และสามารถโหลดไฟล์ที่บันทึกไว้นั้นมาแสดงผลในโปรแกรมได้อย่างถูกต้อง*

## 18. บันทึก
โปรแกรมต้องสามารถบันทึกสถานะของข้อมูลต่าง ๆ ในรูปแบบของไฟล์ csv (comma-separated values) และสามารถโหลดไฟล์ที่บันทึกไว้นั้นมาแสดงผลได้อย่างถูกต้อง
  - [ ] 18.1 (caution) ต้องมีการบันทึกสถานะและข้อมูลที่จำเป็นในรูปแบบไฟล์ csv
  - [ ] **18.2 (5 คะแนน) เมื่อเปิดโปรแกรมใหม่ หรือเมื่อโปรแกรมโหลดไฟล์ csv จะต้องแสดงข้อมูลที่ได้บันทึกไว้อย่างถูกต้อง**

## 19. ข้อมูลในที่ใช้การตรวจสอบ
  ต้องมีข้อมูลเริ่มต้นในการทดสอบระบบ เสมือนว่าระบบถูกใช้งานมาประมาณ 3 เดือน เป็นอย่างน้อย
  
  - [ ] ระบบถูกใช้งานมาประมาณ 3 เดือน
  - [ ] มีข้อมูลผู้ดูแลระบบและข้อมูลเจ้าหน้าที่อย่างน้อย 5 หน่วยงานหน่วยงานละอย่างน้อย 3 บัญชีผู้ใช้
  - [ ] มีข้อมูลหมวดหมู่เรื่องร้องเรียนอย่างน้อย 3 หมวดหมู่ที่มีคุณลักษณะที่แตกต่างกัน
  - [ ] มีข้อมูลผู้ใช้ระบบอย่างน้อย 10 บัญชีแต่ละคนมีภาพที่แตกต่างกัน
  - [ ] ผู้ใช้ระบบอย่างน้อย 3 บัญชีแจ้งเรื่องร้องเรียนอย่างน้อยคนละ 3 หมวดหมู่หมวดหมู่ละอย่างน้อย5 เรื่องร้องเรียน
  *หากมีข้อมูลเริ่มต้นระบบน้อยกว่าที่กำหนด จะไม่ตรวจให้*
  
## 20.การทํางานทุกfeatures ของโปรแกรมจะต้องสมบูรณ์ในตัวโปรแกรมการแก้ไขโค้ดหรือการแก้ไขข้อมูลในไฟล์csv เพื่อให้โปรแกรมทํางานถูกต้องไม่ถือว่าเป็นการทํางานของโปรแกรมที่สมบูรณ์