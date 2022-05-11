package com.loadbean.Import;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/11 14:02
 **/
@Component
@Import({ImportEntiry1.class,ImportEntiry2.class})
public class ImportConfig1 {
}
