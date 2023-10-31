package com.bill24.onlinepaymentsdk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;

import com.bill24.onlinepaymentsdk.customShapeDrawable.CustomShape;
import com.bill24.onlinepaymentsdk.customShapeDrawable.SelectedState;
import com.bill24.onlinepaymentsdk.helper.ChangLanguage;
import com.bill24.onlinepaymentsdk.helper.ConvertColorHexa;
import com.bill24.onlinepaymentsdk.helper.SetFont;
import com.bill24.onlinepaymentsdk.helper.SharePreferenceCustom;
import com.bill24.onlinepaymentsdk.model.BillerModel;
import com.bill24.onlinepaymentsdk.model.CheckoutPageConfigModel;
import com.bill24.onlinepaymentsdk.model.TransactionInfoModel;
import com.bill24.onlinepaymentsdk.model.appearance.AppearanceModel;
import com.bill24.onlinepaymentsdk.model.appearance.darkMode.DarkModeModel;
import com.bill24.onlinepaymentsdk.model.appearance.lightMode.LightModeModel;
import com.bill24.onlinepaymentsdk.model.conts.Constant;
import com.bill24.onlinepaymentsdk.model.conts.CurrencyCode;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SuccessActivity extends AppCompatActivity {

    private AppCompatTextView
            textInvoiceAlreadyPaid, textTranNoTitle,
            textBankRefTitle, textToMerchantTitle, textTranDateTitle,
            textTotalTitle,
            textTranNo, textBankRef, textToMerchant,
    textTranDate, textTotalAmount,textDownload,textShare,textDone,textCurrency;

    private View dashLineFirst,dashLineSecond,dashLineThird;
    private LinearLayoutCompat successContainer;
    private FrameLayout downloadContainer,shareContainer,buttonDoneContainer;
    private TransactionInfoModel transactionInfoModel;
    private BillerModel billerModel=new BillerModel();
    private CheckoutPageConfigModel checkoutPageConfigModel;
    private String language;
    private boolean isLightMode;
    private AppCompatImageView imageShare,imageDownload;
    private LinearLayoutCompat successActivityContainer;
    private LightModeModel lightModeModel;
    private DarkModeModel darkModeModel;

    private void initView(){
        textInvoiceAlreadyPaid=findViewById(R.id.text_invoice_already_paid_suceess);
        textTranNoTitle=findViewById(R.id.text_tran_no_title_success);
        textBankRefTitle=findViewById(R.id.text_bank_ref_title_success);
        textToMerchantTitle=findViewById(R.id.text_to_merchant_title_success);
        textTranDateTitle=findViewById(R.id.text_tran_date_title_success);
        textTotalTitle=findViewById(R.id.text_total_title_success);
        textTranNo=findViewById(R.id.text_tran_no_success);
        textBankRef=findViewById(R.id.text_bank_ref_success);
        textToMerchant=findViewById(R.id.text_to_merchant_success);
        textTranDate=findViewById(R.id.text_tran_date_success);
        textTotalAmount=findViewById(R.id.text_total_amount_success);
        textDownload=findViewById(R.id.text_download_success);
        textShare=findViewById(R.id.text_share_success);
        dashLineFirst=findViewById(R.id.dash_line_first_success);
        dashLineSecond=findViewById(R.id.dash_lin_second_success);
        dashLineThird=findViewById(R.id.dash_line_third_success);
        successContainer=findViewById(R.id.success_container);
        downloadContainer=findViewById(R.id.download_container_suceess);
        shareContainer=findViewById(R.id.share_container_success);
        buttonDoneContainer=findViewById(R.id.button_done_success);
        imageDownload=findViewById(R.id.image_download_success);
        imageShare=findViewById(R.id.image_share_success);
        textDone=findViewById(R.id.text_button_done_success);
        textCurrency=findViewById(R.id.text_currency_success);
        successActivityContainer=findViewById(R.id.success_activity_container);
    }

    private void getDataIntent(){
        language=getIntent().getStringExtra(Constant.KEY_LANGUAGE_CODE);
        isLightMode=getIntent().getBooleanExtra(Constant.IS_LIGHT_MODE,false);
        checkoutPageConfigModel=getIntent().getParcelableExtra(Constant.KEY_CHECKOUT_PAGE_CONFIG);
        transactionInfoModel=getIntent().getParcelableExtra(Constant.KEY_TRANSACTION_INFO);
        //billerModel=getIntent().getParcelableExtra(Constant.KEY_BILLER);

        ChangLanguage.setLanguage(language,this);

        lightModeModel=checkoutPageConfigModel.getAppearance().getLightMode();
        darkModeModel=checkoutPageConfigModel.getAppearance().getDarkMode();


    }

    private void updateFont(){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(this,language);

        textInvoiceAlreadyPaid.setTypeface(typeface);
        textInvoiceAlreadyPaid.setTextSize(16);
        textInvoiceAlreadyPaid.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

        textTranNoTitle.setTypeface(typeface);
        textTranNoTitle.setTextSize(14);

        textBankRefTitle.setTypeface(typeface);
        textBankRefTitle.setTextSize(14);

        textToMerchantTitle.setTypeface(typeface);
        textToMerchantTitle.setTextSize(14);

        textTranDateTitle.setTypeface(typeface);
        textTranDateTitle.setTextSize(14);

        textTotalTitle.setTypeface(typeface);
        textTotalTitle.setTextSize(14);
        textTotalTitle.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


        textTranNo.setTextSize(14);


        textBankRef.setTextSize(14);

        textToMerchant.setTypeface(typeface);
        textToMerchant.setTextSize(14);

        textTranDate.setTypeface(typeface);
        textTranDate.setTextSize(14);

        textTotalAmount.setTypeface(typeface);
        textTotalAmount.setTextSize(16);
        textTotalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


        textCurrency.setTextSize(14);

        textDownload.setTypeface(typeface);
        textDownload.setTextSize(11);

        textShare.setTypeface(typeface);
        textShare.setTextSize(11);

        textDone.setTypeface(typeface);
        textDone.setTextSize(14);


    }

    private void bindData(){
        textTranNo.setText(transactionInfoModel.getTranNo());
        textBankRef.setText(transactionInfoModel.getBankRefId());
        textToMerchant.setText(billerModel.getBillerDisplayName());
        textTranDate.setText(transactionInfoModel.getTranDate());
        textTotalAmount.setText(transactionInfoModel.getTotalAmountDisplay());
        textCurrency.setText(transactionInfoModel.getCurrency());

    }

    private void dashLine(){
        if(isLightMode){
            String dashLine=lightModeModel.getSecondaryColor().getTextColor();
            String dashLineHexa=ConvertColorHexa.convertHex(dashLine);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.LINE);
            //int width=(int) (1 * getResources().getDisplayMetrics().density);
            //int dashWidthHeight=(int) (5 * getResources().getDisplayMetrics().density);

            gradientDrawable.setStroke(1,
                    Color.parseColor(dashLineHexa),
                    15,
                    15);

            dashLineFirst.setBackground(gradientDrawable);
            dashLineSecond.setBackground(gradientDrawable);
            dashLineThird.setBackground(gradientDrawable);
        }
        else {
            String dashLine=darkModeModel.getSecondaryColor().getTextColor();
            String dashLineHexa=ConvertColorHexa.convertHex(dashLine);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.LINE);
            //int width=(int)(1 * this.getResources().getDisplayMetrics().density);
            //int dashWidthHeight=(int)(5 * this.getResources().getDisplayMetrics().density);

            gradientDrawable.setStroke(1,
                    Color.parseColor(dashLineHexa),
                    15,
                    15);

            dashLineFirst.setBackground(gradientDrawable);
            dashLineSecond.setBackground(gradientDrawable);
            dashLineThird.setBackground(gradientDrawable);
        }


    }

    private Bitmap convertLayoutToImage(View view){
        AppCompatTextView invoiceAlreayPaid=view.findViewById(R.id.text_invoice_already_paid_share);
        AppCompatTextView tranNoTitle=view.findViewById(R.id.text_tran_no_title_share);
        AppCompatTextView bankRefTitle=view.findViewById(R.id.text_bank_ref_title_share);
        AppCompatTextView toMerchantTitle=view.findViewById(R.id.text_to_merchant_title_share);
        AppCompatTextView tranDateTitle=view.findViewById(R.id.text_to_merchant_title_share);
        AppCompatTextView totalTitle=view.findViewById(R.id.text_total_title_share);
        AppCompatTextView transactionNo=view.findViewById(R.id.text_tran_no_share);
        AppCompatTextView bankRef=view.findViewById(R.id.text_bank_ref_share);
        AppCompatTextView merchantName=view.findViewById(R.id.text_to_merchant_share);
        AppCompatTextView transactionDate=view.findViewById(R.id.text_tran_date_share);
        AppCompatTextView totalAmount=view.findViewById(R.id.text_total_amount_share);
        AppCompatTextView currency=view.findViewById(R.id.text_currency_share);


            //set value
            transactionNo.setText(transactionInfoModel.getTranNo());
            bankRef.setText(transactionInfoModel.getBankRefId());
            merchantName.setText(billerModel.getBillerDisplayName());
            transactionDate.setText(transactionInfoModel.getTranDate());
            totalAmount.setText(transactionInfoModel.getTotalAmountDisplay());
            currency.setText(transactionInfoModel.getCurrency());

            //use to update font
            SetFont font=new SetFont();
            Typeface typeface=font.setFont(this,language);

            invoiceAlreayPaid.setTypeface(typeface);
            invoiceAlreayPaid.setTextSize(16);
            invoiceAlreayPaid.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

            tranNoTitle.setTypeface(typeface);
            tranNoTitle.setTextSize(14);

            bankRefTitle.setTypeface(typeface);
            bankRefTitle.setTextSize(14);

            toMerchantTitle.setTypeface(typeface);
            toMerchantTitle.setTextSize(14);

            tranDateTitle.setTypeface(typeface);
            tranDateTitle.setTextSize(14);

            totalTitle.setTypeface(typeface);
            totalTitle.setTextSize(14);
            totalTitle.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


            transactionNo.setTextSize(14);


            bankRef.setTextSize(14);

            merchantName.setTypeface(typeface);
            merchantName.setTextSize(14);

            transactionDate.setTypeface(typeface);
            transactionDate.setTextSize(14);

            totalAmount.setTypeface(typeface);
            totalAmount.setTextSize(16);
            totalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);


            currency.setTextSize(14);

            view.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
            return  Bitmap.createBitmap(view.getMeasuredWidth(),view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);



    }

    private void  customSnackBar(int image,String desc){
        Snackbar customSnackbar = Snackbar.make(findViewById(R.id.snackbar_success_container), "",Snackbar.LENGTH_SHORT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) customSnackbar.getView();
        View customView = getLayoutInflater().inflate(R.layout.snackbar_success_custom_layout, null);
        snackbarLayout.setBackgroundColor(Color.TRANSPARENT);//remove snackbar background
        snackbarLayout.addView(customView, 0);

        //update font family
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(this,language);

        // Customize the content and appearance of the custom layout
        AppCompatTextView textView = customView.findViewById(R.id.custom_snackbar_desc);
        textView.setTypeface(typeface);
        textView.setText(desc);

        AppCompatImageView imageView=customView.findViewById(R.id.custom_snackbar_icon);
        imageView.setImageResource(image);

        customSnackbar.show();
    }
    private void downloadKHQR(Bitmap bitmap){
        Date now = new Date();
        long currentTimeInMilliseconds = System.currentTimeMillis();
        int microseconds = (int) ((currentTimeInMilliseconds % 1000) * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String formattedDateTime = dateFormat.format(now);

        String imageTitle="KHQR Image "+formattedDateTime+microseconds;
        String imageUrl= MediaStore.Images.Media.insertImage(this.getContentResolver(),bitmap,imageTitle,"");

        if(imageUrl!=null){
            customSnackBar(R.drawable.check_circle_24px,getResources().getString(R.string.image_saved));
        }else {
            customSnackBar(R.drawable.error_24px,getResources().getString(R.string.image_unsave));
        }
    }

    private void shareKHQR(Bitmap bitmap){
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp_image", ".jpg", this.getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            Uri uri = FileProvider.getUriForFile(this, Constant.AUTHORITY, tempFile);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void successContainerShape(){

        if(isLightMode){

            String successActivityColor=lightModeModel.getSecondaryColor().getBackgroundColor();
            String successActivityHexa=ConvertColorHexa.convertHex(successActivityColor);
            successActivityContainer.setBackgroundColor(Color.parseColor(successActivityHexa));


            String successContainerColor=lightModeModel.getPrimaryColor().getBackgroundColor();
            String successContainerHexa= ConvertColorHexa.convertHex(successContainerColor);
            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(successContainerHexa),20,this);
            successContainer.setBackground(shape);
        }else {
            String successActivityColor=darkModeModel.getSecondaryColor().getBackgroundColor();
            String successActivityHexa=ConvertColorHexa.convertHex(successActivityColor);
            successActivityContainer.setBackgroundColor(Color.parseColor(successActivityHexa));

            String successContainerColor=darkModeModel.getPrimaryColor().getBackgroundColor();
            String successContainerHexa= ConvertColorHexa.convertHex(successContainerColor);
            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(successContainerHexa),20,this);
            successContainer.setBackground(shape);
        }
    }

    private void downloadShareContainerShape(){
        if(isLightMode){


            String bgDownloadShare=lightModeModel.getButton().getActionButton().getBackgroundColor();
            String bgDownloadShareHexa=ConvertColorHexa.convertHex(bgDownloadShare);

            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(bgDownloadShareHexa),10,this);
            String downloadShareSelectedColor=ConvertColorHexa.getFiftyPercentColor(bgDownloadShare);
            ShapeDrawable shape1=CustomShape.applyShape(Color.parseColor(downloadShareSelectedColor),10,this);


            StateListDrawable selectorDownload= SelectedState.selectedSate(shape,shape1);
            downloadContainer.setBackground(selectorDownload);

            StateListDrawable selectorShare=SelectedState.selectedSate(shape,shape1);


            shareContainer.setBackground(selectorShare);

            //apply icon color
            String iconColor=lightModeModel.getButton().getActionButton().getTextColor();
            String iconColorHexa=ConvertColorHexa.convertHex(iconColor);
            ColorFilter colorFilterFavicon=new PorterDuffColorFilter(Color.parseColor(iconColorHexa), PorterDuff.Mode.SRC_ATOP);
            imageShare.setColorFilter(colorFilterFavicon);
            imageDownload.setColorFilter(colorFilterFavicon);

            //download share
            String downloadShareColor=lightModeModel.getSecondaryColor().getTextColor();
            String downloadShareHexa=ConvertColorHexa.convertHex(downloadShareColor);

            textDownload.setTextColor(Color.parseColor(downloadShareHexa));
            textShare.setTextColor(Color.parseColor(downloadShareHexa));
        }else {


            String bgDownloadShare=darkModeModel.getButton().getActionButton().getBackgroundColor();
            String bgDownloadShareHexa=ConvertColorHexa.convertHex(bgDownloadShare);

            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(bgDownloadShareHexa),10,this);
            String downloadShareSelectedColor=ConvertColorHexa.getFiftyPercentColor(bgDownloadShare);
            ShapeDrawable shape1=CustomShape.applyShape(Color.parseColor(downloadShareSelectedColor),10,this);


            StateListDrawable selectorDownload= SelectedState.selectedSate(shape,shape1);
            downloadContainer.setBackground(selectorDownload);

            StateListDrawable selectorShare=SelectedState.selectedSate(shape,shape1);


            shareContainer.setBackground(selectorShare);

            //apply icon color
            String iconColor=darkModeModel.getButton().getActionButton().getTextColor();
            String iconColorHexa=ConvertColorHexa.convertHex(iconColor);
            ColorFilter colorFilterFavicon=new PorterDuffColorFilter(Color.parseColor(iconColorHexa), PorterDuff.Mode.SRC_ATOP);
            imageShare.setColorFilter(colorFilterFavicon);
            imageDownload.setColorFilter(colorFilterFavicon);

            //download share
            String downloadShareColor=darkModeModel.getSecondaryColor().getTextColor();
            String downloadShareHexa=ConvertColorHexa.convertHex(downloadShareColor);

            textDownload.setTextColor(Color.parseColor(downloadShareHexa));
            textShare.setTextColor(Color.parseColor(downloadShareHexa));

        }

    }

    private void buttonDoneShape(){
        if(isLightMode){
            String bgButton=lightModeModel.getButton().getRetryButton().getBackgroundColor();
            String bgButtonHexa=ConvertColorHexa.convertHex(bgButton);
            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(bgButtonHexa),10,this);

            String buttonSelectColor=ConvertColorHexa.getFiftyPercentColor(bgButton);
            ShapeDrawable buttonSelected=CustomShape.applyShape(Color.parseColor(buttonSelectColor),10,this);

            StateListDrawable selector= SelectedState.selectedSate(shape,buttonSelected);
            buttonDoneContainer.setBackground(selector);

            //text button try again
            String textButtonColor=lightModeModel.getButton().getRetryButton().getTextColor();
            String textButtonColorHexa=ConvertColorHexa.convertHex(textButtonColor);
            textDone.setTextColor(Color.parseColor(textButtonColorHexa));

        }else {

            String bgButton=darkModeModel.getButton().getRetryButton().getBackgroundColor();
            String bgButtonHexa=ConvertColorHexa.convertHex(bgButton);
            ShapeDrawable shape= CustomShape.applyShape(Color.parseColor(bgButtonHexa),10,this);
            String buttonSelectColor=ConvertColorHexa.getFiftyPercentColor(bgButton);
            ShapeDrawable buttonSelected=CustomShape.applyShape(Color.parseColor(buttonSelectColor),10,this);

            StateListDrawable selector= SelectedState.selectedSate(shape,buttonSelected);
            buttonDoneContainer.setBackground(selector);

            //text button try again
            String textButtonColor=darkModeModel.getButton().getRetryButton().getTextColor();
            String textButtonColorHexa=ConvertColorHexa.convertHex(textButtonColor);
            textDone.setTextColor(Color.parseColor(textButtonColorHexa));
        }

    }


    private void applyTextStyle(){
        if(isLightMode){
            String invoiceTitleColor=lightModeModel.getPrimaryColor().getTextColor();
            String invoiceHaxa=ConvertColorHexa.convertHex(invoiceTitleColor);
            textInvoiceAlreadyPaid.setTextColor(Color.parseColor(invoiceHaxa));

            String otherTextColor=lightModeModel.getSecondaryColor().getTextColor();
            String otherTextColorHexa=ConvertColorHexa.convertHex(otherTextColor);
            textTranNoTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textBankRefTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textToMerchantTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranDateTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranNo.setTextColor(Color.parseColor(otherTextColorHexa));
            textBankRef.setTextColor(Color.parseColor(otherTextColorHexa));
            textToMerchant.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranDate.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalAmount.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
            textCurrency.setTextColor(Color.parseColor(otherTextColorHexa));

        }
        else {
            String invoiceTitleColor=darkModeModel.getPrimaryColor().getTextColor();
            String invoiceHaxa=ConvertColorHexa.convertHex(invoiceTitleColor);
            textInvoiceAlreadyPaid.setTextColor(Color.parseColor(invoiceHaxa));

            String otherTextColor=darkModeModel.getSecondaryColor().getTextColor();
            String otherTextColorHexa=ConvertColorHexa.convertHex(otherTextColor);
            textTranNoTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textBankRefTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textToMerchantTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranDateTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalTitle.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranNo.setTextColor(Color.parseColor(otherTextColorHexa));
            textBankRef.setTextColor(Color.parseColor(otherTextColorHexa));
            textToMerchant.setTextColor(Color.parseColor(otherTextColorHexa));
            textTranDate.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalAmount.setTextColor(Color.parseColor(otherTextColorHexa));
            textTotalAmount.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
            textCurrency.setTextColor(Color.parseColor(otherTextColorHexa));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);



        initView();

        getDataIntent();



        dashLine();

        bindData();

        updateFont();

        successContainerShape();



        downloadShareContainerShape();

        buttonDoneShape();

        applyTextStyle();

        View layoutImage=getLayoutInflater().inflate(R.layout.download_share_success_image_layout,null);

        downloadContainer.setOnClickListener(v->{

            Bitmap bitmap=convertLayoutToImage(layoutImage);
            Canvas canvas=new Canvas(bitmap);
            layoutImage.draw(canvas);

            //Save Image into Gallerry
            downloadKHQR(bitmap);

        });


        shareContainer.setOnClickListener(v->{



            Bitmap bitmap=convertLayoutToImage(layoutImage);
            Canvas canvas=new Canvas(bitmap);
            layoutImage.draw(canvas);

            shareKHQR(bitmap);

        });

    }




}