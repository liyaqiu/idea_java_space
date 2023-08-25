package org.test.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author eric
 * @since 2023-08-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "tb_person")
public class Person implements Serializable {

    /**
     * 唯一标识
     */
    @Id
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}
