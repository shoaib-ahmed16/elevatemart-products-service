package com.products.service.elevatemartproductsservice.repository.customize.impl;

import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import com.products.service.elevatemartproductsservice.repository.customize.ProductRepositoryCustom;

import java.util.List;

//@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Override
    public List<ProductDto> findProductBySearchCriteria(SearchFilter searchFilter) {
        return null;
    }
    /*
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<ProductDto> findProductBySearchCriteria(SearchFilter searchFilter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);
            List<Filters>  filters =searchFilter.getFilterGroup().getFilterFieldsList();
            List<Predicate> predicates = new ArrayList<>();
            Predicate finalCondition = builder.conjunction();
            for(Filters filter:filters) {
                String fieldName = filter.getFilterField();
                Object filterValue = filter.getFilterValue();
                if (fieldName.equals(ProductVariables.ID.getValue())) {
                    predicates.add(builder.equal(root.get(ProductVariables.ID.getValue()), (Long) filterValue));
                } else if (fieldName.equals(ProductVariables.SKU.getValue())) {
                    predicates.add(builder.equal(root.get(ProductVariables.SKU.getValue()), (String) filterValue));
                } else if (fieldName.equals(ProductVariables.NAME.getValue())) {
                    predicates.add(builder.equal(root.get(ProductVariables.NAME.getValue()), (String) filterValue));
                }else if (fieldName.equals(ProductVariables.PRICE.getValue())) {
                    predicates.add(builder.equal(root.get(ProductVariables.PRICE.getValue()), (Double) filterValue));
                }
                else if("groupList.name".equals(filter.getFilterField())){
                    Join<Product, Category> groupJoin = root.join("categoryList");
                    Predicate nameCondition = builder.equal(groupJoin.get("name"), filter.getFilterValue());
                    finalCondition = builder.and(finalCondition, nameCondition);

                }
            }
            // Build query to be execute on the product table.
            query.select(root).where(predicates.toArray(new Predicate[0])).where(finalCondition);

            List<Product> productList = session.createQuery(query).getResultList();
            List<ProductDto> productDtoList = new ArrayList<>();
            for(Product p:productList){
                productDtoList.add(ProductMapper.convertToDto(p));
            }
            return productDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

     */
}
