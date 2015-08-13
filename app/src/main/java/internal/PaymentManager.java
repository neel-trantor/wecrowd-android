package internal;

import android.content.Context;
import android.graphics.Bitmap;

import com.wepay.android.SignatureHandler;
import com.wepay.android.SwiperHandler;
import com.wepay.android.TokenizationHandler;
import com.wepay.android.WePay;
import com.wepay.android.models.Config;
import com.wepay.android.models.PaymentInfo;

/**
 * Created by zachv on 7/27/15.
 * Wecrowd Android
 */
public class PaymentManager {
    private static final String CLIENT_ID = "116876";
    private static Config config = null;
    private static WePay wepay = null;

    public static void tokenizeInfo(Context context, PaymentInfo paymentInfo,
                                    final TokenizationHandler handler) {
        initializeMembersFromContext(context);

        wepay.tokenize(paymentInfo, handler);
    }

    public static void startCardSwipeTokenization(Context context, SwiperHandler swiperHandler,
                                                  TokenizationHandler tokenizationHandler)
    {
        initializeMembersFromContext(context);

        wepay.startSwiperForTokenizing(swiperHandler, tokenizationHandler);
    }

    public static void stopCardReader(Context context) {
        initializeMembersFromContext(context);

        wepay.stopSwiper();
    }

    public static void storeSignatureImage(Context context,
                                           Bitmap image,
                                           String checkoutID,
                                           SignatureHandler signatureHandler)
    {
        initializeMembersFromContext(context);

        wepay.storeSignatureImage(image, checkoutID, signatureHandler);
    }

    private static void initializeMembersFromContext(Context context) {
        if (config == null || wepay == null) {
            config = new Config(context, CLIENT_ID, Config.ENVIRONMENT_STAGING);
            config.setRestartSwiperAfterSwipeGeneralError(true);
            wepay = new WePay(config);
        } else if (config.getContext() != context) {
            config = new Config(context, CLIENT_ID, Config.ENVIRONMENT_STAGING);
            config.setRestartSwiperAfterSwipeGeneralError(true);
            wepay = new WePay(config);
        }
    }
}
