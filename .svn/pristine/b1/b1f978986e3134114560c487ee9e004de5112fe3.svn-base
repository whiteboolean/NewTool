package com.mtool.toolslib.base.view.custom.editText;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;


import com.mtool.toolslib.base.R;

import java.util.ArrayList;

/***
 * EDIT TEXT 支持 动态内容分割
 * 依据不同类型分割出不同字串
 * 像是
 * 金额 10000 => 10,000
 * 电话 01712341234 => 017-1234-1234
 * 帐号 0000000000000000 -> 0000-0000-0000-0000
 */
public class EditTextWithChecking extends EditTextWithDrawableListener {

    private static final String TAG = "DivisionEditText";

    private static final int PHONE_TYPE = 1;

    private static final int BANK_TYPE = 2;

    private static final int MONEY_TYPE = 3;

    private static final int DEFAULT_MONEY_LENGTH = 20;

    private static final int DEFAULT_BANK_CARD_LENGTH = 20;

    private static final int DEFAULT_PHONE_NUMBER_LENGTH = 11;

    private static final int DEFAULT_DIVIDE_LENGTH = 4;

    private static final String DEFAULT_DIVIDE_SYMBOL = "-";

    private static final int DEFAULT_DIVIDE_MONEY_LENGTH = 3;

    private static final String DEFAULT_DIVIDE_MONEY_SYMBOL = ",";


    private String mSperator;
    // 存放需分隔处文本窗独
    private ArrayList<Integer> groupCoords = new ArrayList<Integer>();
    // 记录上次增减文本的长度，判断本次添加还是删除
    private int mLastLength = 0;
    // xml文件设置的输入长度
    private int mTotalLength;
    // 记录加上分隔符后总长度
    private int mLength;
    // 当前输入类型
    private int mType;
    private boolean isStop = false;
    private boolean isEnable = false;
    public EditTextWithChecking(Context context) {
        super(context);
    }

    public EditTextWithChecking(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.DivisionEditText);
        if (typedArray != null) {

            isEnable = typedArray.getBoolean(R.styleable.DivisionEditText_divion_enable,false);
            if(isEnable){
                mType = typedArray.getInt(R.styleable.DivisionEditText_divion_type, BANK_TYPE);
                mTotalLength = typedArray.getInteger(R.styleable.DivisionEditText_divion_totalLength, DEFAULT_BANK_CARD_LENGTH);
                mSperator = typedArray.getString(R.styleable.DivisionEditText_divion_sperator);

                if (TextUtils.isEmpty(mSperator)) {
                    mSperator = DEFAULT_DIVIDE_SYMBOL;
                }
                mType = typedArray.getInt(R.styleable.DivisionEditText_divion_type, BANK_TYPE);
                if (mType == MONEY_TYPE) {
                    int mode = mTotalLength % DEFAULT_MONEY_LENGTH;
                    int divider = mTotalLength / DEFAULT_DIVIDE_MONEY_LENGTH;
                    int groupCoordsLength = mode == 0 ? divider - 1 : divider;
                    this.mLength = mTotalLength + groupCoordsLength;
                    for (int i = 1; i < groupCoordsLength + 1; i++) {
                        groupCoords.add(i * (DEFAULT_DIVIDE_MONEY_LENGTH + 1) - 1);
                    }
                    typedArray.recycle();
                } else if (mType == BANK_TYPE) {
                    // 计算需要几个分隔符及每个对应的长度
                    int mode = mTotalLength % DEFAULT_BANK_CARD_LENGTH;
                    int divider = mTotalLength / DEFAULT_DIVIDE_LENGTH;
                    int groupCoordsLength = mode == 0 ? divider - 1 : divider;
                    this.mLength = mTotalLength + groupCoordsLength;
                    for (int i = 1; i < groupCoordsLength + 1; i++) {
                        groupCoords.add(i * (DEFAULT_DIVIDE_LENGTH + 1) - 1);
                    }
                    typedArray.recycle();
                } else {
                    initPhoneGroupCoords();
                    this.mLength = DEFAULT_PHONE_NUMBER_LENGTH + groupCoords.size();
                }

                setMaxWidth(mLength);
                addTextChangedListener(new DivisionTextWatcher());
            }
        }
    }

    private void initPhoneGroupCoords() {
        //这里偷懒了，不计算了，手机号默认这么分了。。。。懒。。。
        groupCoords.add(3);
        groupCoords.add(8);
    }

    public EditTextWithChecking(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取文本框输入的内容，自动替换掉分隔符
     *
     * @return
     */
    public String getContent() {
        return getText().toString().trim().replace(mSperator, "");
    }

    private class DivisionTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG, "s = " + s.toString() + " , s.length = " + s.length() + " , mLength = " + mLength);

            if (mType == MONEY_TYPE) {

                String reformStr = s.toString().replace(",", "");
                if (reformStr.length() <= mLength) {
                    if (mLastLength != reformStr.length() || !isStop) {
                        switch (reformStr.length()) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                                isStop = true;
                                mLastLength = reformStr.length();
                                setText(reformStr.toString());
                                break;
                            case 4:
                            case 5:
                            case 6:
                                isStop = true;
                                mLastLength = reformStr.length();
                                reformStr = reformStr.toString().substring(0, reformStr.length() - 3) + mSperator + reformStr.toString().substring(reformStr.length() - 3, reformStr.length());
                                setText(reformStr.toString());
                                break;
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                                isStop = true;
                                mLastLength = reformStr.length();
                                reformStr = reformStr.toString().substring(0, reformStr.length() - 6) + mSperator + reformStr.toString().substring(reformStr.length() - 6, reformStr.length() - 3) + mSperator + reformStr.toString().substring(reformStr.length() - 3, reformStr.length());
                                setText(reformStr.toString());
                                break;
                        }
                    }
                } else {
                    isStop = true;
                    reformStr = reformStr.toString().substring(0, reformStr.length() - 1);
                    setText(reformStr.toString());
                }
                try {
                    setSelection(reformStr.length());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                if (s.length() <= mLength) {
                    if (mLastLength == 0) {
                        mLastLength = s.length();
                    }
                    if (groupCoords.contains(s.length() - 1)) {
                        if (s.length() < mLastLength) {
                            // delete
                            s = s.toString().substring(0, s.length() - 1);
                            setText(s.toString());
                        } else if (s.length() > mLastLength) {
                            // add
                            s = s.toString().substring(0, s.length() - 1) + mSperator + s.toString().substring(s.length() - 1);
                            setText(s.toString());
                        }
                    }
                } else {
                    s = s.toString().substring(0, s.length() - 1);
                    setText(s.toString());
                }
                try {
                    setSelection(s.length());
                    mLastLength = s.length();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    /**
     * 银行卡四位加空格
     *
     * @param mEditText
     */
    protected void bankCardNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '-') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    mEditText.setText(str);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }
}
