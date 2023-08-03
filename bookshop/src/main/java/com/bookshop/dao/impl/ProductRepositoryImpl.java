package com.bookshop.dao.impl;

import com.bookshop.dao.ProductRepository;
import com.bookshop.dto.ProductDTO;
import com.bookshop.entity.Product;
import com.bookshop.mapper.ProductMapper;
import com.bookshop.mapper.ProductMapper1;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;

public class ProductRepositoryImpl extends AbstractDAO<Product> implements ProductRepository {
    @Override
    public List<Product> findFirst12ByCategoryNameCategoryContainingIgnoreCaseOrderByIdDesc(String tieng_anh) {
       StringBuilder sql = new StringBuilder("SELECT p.id,price, wareHouse,nameProduct, infoProduct,category_id, nameCategory, nxb_id, n.nameNxb ");
       sql.append(" FROM products p");
       sql.append(" INNER JOIN categories c ON p.category_id = c.id");
       sql.append(" INNER JOIN nxbs n ON p.nxb_id = n.id");
       sql.append(" WHERE LOWER(c.nameCategory) LIKE LOWER('%tieng anh%')");
       sql.append(" ORDER BY p.id DESC");
       sql.append(" LIMIT 12");
       return  query(sql.toString(), new ProductMapper());
    }

    @Override
    public Product findProductById(long id) {
        StringBuilder sql = new StringBuilder("SELECT p.id,  price, wareHouse, unitSelling, nameProduct, infoProduct, nxb_id, n.nameNxb, category_id, nameCategory ");
        sql.append(" FROM products AS p");
        sql.append(" INNER JOIN nxbs AS n ON n.id = p.nxb_id");
        sql.append(" INNER JOIN categories AS c ON c.id = p.category_id");
        sql.append(" WHERE p.id = ?");
        List<Product> listproducts = query(sql.toString(), new ProductMapper1(), id);
        return listproducts.isEmpty()?null : listproducts.get(0);
    }

    @Override
    public Product findById(long id) {
        StringBuilder sql = new StringBuilder("SELECT * ");
        sql.append(" FROM products AS p");
        sql.append(" WHERE p.id = ?");
        List<Product> listproducts = query(sql.toString(), new ProductMapper(), id);
        return listproducts.isEmpty()?null : listproducts.get(0);
    }

    @Override
    public List<Product> findAllProductPageByCategoryId(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products ");
        sql.append(" WHERE category_id = " + pageble.getSearchProduct().getCategory_id());
        if(pageble.getSearchProduct().getPrice() != null){
            sql.append(" AND price " + pageble.getSearchProduct().getPrice());
        }
        if(pageble.getSearchProduct().getNxb_id() != null){
            sql.append(" AND nxb_id = " + pageble.getSearchProduct().getNxb_id());
        }
        if(pageble.getOffset() != null && pageble.getLimit() != null){
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");//Lay tu vi tri n-> m
        }
        return query(sql.toString(), new ProductMapper1(),pageble);
    }


    @Override
    public List<Product> findProductAndNxbByCategoryId(long category_id) {
        StringBuilder sql = new StringBuilder("SELECT  p.id, nameProduct, wareHouse, price, infoProduct, nameNxb, nxb_id, category_id, nameCategory FROM products p ");
        sql.append(" INNER JOIN nxbs n ON p.nxb_id = n.id ");
        sql.append(" INNER JOIN categories c ON p.category_id = c.id");
        sql.append(" WHERE category_id = ?");
        return query(sql.toString(), new ProductMapper1(), category_id);
    }


    @Override
    public int getTotalItemByPageble(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS totalItem FROM products  ");
        sql.append(" WHERE category_id = " + pageble.getSearchProduct().getCategory_id());
        if(pageble.getSearchProduct().getPrice() != null){
            sql.append(" " + pageble.getSearchProduct().getPrice());
        }
        if(pageble.getSearchProduct().getNxb_id() != null){
            sql.append(" " + pageble.getSearchProduct().getNxb_id());
        }
        return count(sql.toString());
    }

    @Override
    public List<Product> findByIdIn(Set<Long> idList) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products ");
        sql.append("WHERE id IN (");
        for(int i = 0; i < idList.size(); i++){
            sql.append("?");
            if(i != idList.size() -1){
                sql.append(", ");
            }
        }
        sql.append(")");
        return query(sql.toString(), new ProductMapper(), idList.toArray());
    }

    @Override
    public List<Product> findAll() {
        StringBuilder sql = new StringBuilder("SELECT p.id, nameProduct, price, warehouse, infoProduct, nameNxb, nxb_id, category_id, nameCategory");
        sql.append("  FROM products p");
        sql.append(" INNER JOIN nxbs n ON p.nxb_id = n.id");
        sql.append(" INNER JOIN categories c ON p.category_id = c.id");
         return query(sql.toString(), new ProductMapper1() );

    }

    @Override
    public List<Product> findAllProductByFilter(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT p.id, nameProduct, price, warehouse, infoProduct, nameNxb, nxb_id, category_id, nameCategory  ");
        sql.append(" FROM products p");
        sql.append(" INNER JOIN nxbs n ON p.nxb_id = n.id");
        sql.append(" INNER JOIN categories c ON p.category_id = c.id");
//        if(pageble.getSearchProduct().getCategory_id() == 0){
//            sql.append(" WHERE category_id = " + 1);
//        }else {
//            sql.append(" WHERE category_id = " + pageble.getSearchProduct().getCategory_id());
//        }
        if(pageble.getOffset() != null && pageble.getLimit() != null){
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");//Lay tu vi tri n-> m
        }
        return query(sql.toString(), new ProductMapper1(),pageble);
    }

    @Override
    public int getTotalItemByFilter(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS totalItem FROM products p  ");
        sql.append(" INNER JOIN nxbs n ON p.nxb_id = n.id");
        sql.append(" INNER JOIN categories c ON p.category_id = c.id");
//        if(pageble.getSearchProduct().getCategory_id() == 0){
//            sql.append(" WHERE category_id = " + 1);
//        }else{
//            sql.append(" WHERE category_id = " + pageble.getSearchProduct().getCategory_id());
//        }
        return count(sql.toString());
    }

    @Override
    public void saveNew(ProductDTO productDTO) {
        StringBuilder sql = new StringBuilder("INSERT INTO products (nameProduct,infoProduct, price, wareHouse, nxb_id, category_id) ");
        sql.append("VALUES(?,?,?,?,?,?)");
        insert(sql.toString(), productDTO.getNameProduct(), productDTO.getInfoProduct(), productDTO.getPrice(),
                productDTO.getWareHouse(), productDTO.getNxb_id(), productDTO.getCategory_id());
    }

    @Override
    public Product findProductByNameProduct(String nameProduct) {
        StringBuilder sql = new StringBuilder("SELECT * FROM  products");
        sql.append(" WHERE nameProduct = ? ");
        List<Product> listProduct = query(sql.toString(), new ProductMapper(), nameProduct);
        return listProduct.isEmpty()?null: listProduct.get(0);
    }

    @Override
    public void saveUpdate(ProductDTO productDTO) {
        StringBuilder sql = new StringBuilder("UPDATE products ");
        sql.append(" SET nameProduct = ? , infoProduct = ?, wareHouse = ?, price = ?, nxb_id = ? , category_id = ?");
        sql.append(" WHERE id = ?");
        update(sql.toString(), productDTO.getNameProduct(), productDTO.getInfoProduct(), productDTO.getWareHouse(),
                productDTO.getPrice(), productDTO.getNxb_id(), productDTO.getCategory_id(), productDTO.getId());
    }

    @Override
    public void delete(String product_id) {
        StringBuilder sql = new StringBuilder("DELETE FROM products");
        sql.append(" WHERE id = ?");
        update(sql.toString(),product_id);
    }
}
