package com.loadbean.importselector;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/11 13:21
 **/
@Component
@Import(MyImportSelector.class)
public class ImportConfig {
}
