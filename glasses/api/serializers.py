from rest_framework import serializers
from glasses.models import GlassesModel, GlassesVariant, GlassesForm, GlassesFrame, ExtraOption

class GlassesVariantSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesVariant
        fields = '__all__'

class GlassesVariantListSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesVariant
        fields = ('pk', 'name', 'avatar')

class GlassesFrameSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesFrame
        fields = '__all__'

class GlassesFormSerializer(serializers.ModelSerializer):
    class Meta:
        model = GlassesForm
        fields = '__all__'

class ExtraOptionSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExtraOption
        fields = '__all__'


class GlassesModelSerializer(serializers.ModelSerializer):
    variants = GlassesVariantListSerializer(many = True)
    options = ExtraOptionSerializer(many = True)

    form = GlassesFormSerializer()
    frame = GlassesFrameSerializer()

    class Meta:
        model = GlassesModel
        fields = '__all__'

class GlassesModelListSerializer(serializers.ModelSerializer):
    variants = GlassesVariantListSerializer(many = True)

    class Meta:
        model = GlassesModel
        fields = ('pk', 'name', 'variants', 'cost', 'form', 'frame')